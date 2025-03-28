package com.althaaf.pokeball.screen.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.althaaf.pokeball.R
import com.althaaf.pokeball.core.domain.entity.User
import com.althaaf.pokeball.databinding.ActivityAuthBinding
import com.althaaf.pokeball.screen.home.MainActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()
    private var stateScreen = LOGIN_STATE
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupReactiveUI()
        setupAction()
    }

    private fun setupReactiveUI() {
        val usernameStream = RxTextView.textChanges(binding.ietUsername)
            .skipInitialValue()
            .map { username ->
                username.isEmpty() || username.isBlank()
            }

        val usernameSubscribe = usernameStream.subscribe { usernameisEmpty ->
            if (usernameisEmpty)
                binding.etInputUsername.error = "Username must be filled"
            else
                binding.etInputUsername.error = null
        }

        val passwordStream = RxTextView.textChanges(binding.ietPassword)
            .skipInitialValue()
            .map { password ->
                password.isEmpty() || password.length < 7
            }

        val passwordSubscribe = usernameStream.subscribe { passwordIsNotValid ->
            if (passwordIsNotValid)
                binding.etInputUsername.error = "password must have at least 8 characters"
            else
                binding.etInputPassword.error = null
        }

        val invalidFieldsStream = Observable.combineLatest(
            usernameStream,
            passwordStream
        ) { usernameInvalid: Boolean, passwordInvalid: Boolean ->
            !usernameInvalid && !passwordInvalid
        }

        val invalidFieldsStreamSubscription =invalidFieldsStream.subscribe { isValid ->
            binding.btnAuth.isEnabled = isValid
        }

        compositeDisposable.addAll(usernameSubscribe, passwordSubscribe, invalidFieldsStreamSubscription)
    }

    private fun setupAction() {
        binding.btnDirect.setOnClickListener {
            if(stateScreen == LOGIN_STATE) {
                stateScreen = LOGIN_STATE
                binding.tvHeadlineAuth.text = "Login to your account below"
                binding.btnAuth.text = "Login"
            } else {
                stateScreen = REGISTER_STATE
                binding.tvHeadlineAuth.text = "Register your account"
                binding.btnAuth.text = "Register"
            }
        }

        binding.btnAuth.setOnClickListener {
            val usernameValue = binding.etInputUsername.editText?.text?.toString()
            val passwordValue = binding.etInputPassword.editText?.text?.toString()

            when {
                usernameValue?.isEmpty() == true -> {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                }

                passwordValue?.isEmpty() == true -> {
                    Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val user = User(
                        username = usernameValue!!,
                        password = passwordValue!!
                    )

                    if(stateScreen == LOGIN_STATE) {
                        loginUser(user)
                    } else {
                        registerUser(user)
                    }
                }
            }
        }
    }

    private fun registerUser(user: User) {
        authViewModel.registerUser(
            user.username, user.password
        )
        stateScreen = LOGIN_STATE
        binding.tvHeadlineAuth.text = "Login to your account below"
        binding.btnAuth.text = "Login"
    }

    private fun loginUser(user: User) {
        lifecycleScope.launch {
            authViewModel.login(
                user.username, user.password
            ).collect {
                if(it.username == user.username) {
                    val intent = Intent(this@AuthActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(this@AuthActivity, "Wrong username or password", Toast.LENGTH_SHORT).show()
                    return@collect
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        const val LOGIN_STATE = 1
        const val REGISTER_STATE = 2
    }
}