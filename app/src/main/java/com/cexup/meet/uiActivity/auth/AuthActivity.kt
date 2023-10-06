package com.cexup.meet.uiActivity.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.cexup.meet.R
import com.cexup.meet.data.MeetingRoom
import com.cexup.meet.databinding.ActivityAuthBinding
import com.cexup.meet.uiActivity.listmeeting.ListMeetingActivity
import com.cexup.meet.uiActivity.main.MainActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        viewModel.isLoading.observe(this){isLoading ->
            if (isLoading){
                binding.authPB.isVisible = true
                binding.loginBtn.isVisible = false
                binding.tvError.isVisible = false
            }
            else{
                binding.authPB.isVisible = false
                binding.loginBtn.isVisible = true
                if (viewModel.errorMSG.isNotEmpty()){
                    binding.tvError.isVisible = true
                    binding.tvError.text = viewModel.errorMSG
                }
            }
        }

        viewModel.loggedIn.observe(this){isLoggedIn ->
            if (isLoggedIn){
                startActivity(Intent(this, ListMeetingActivity::class.java))
            }
        }

        binding.loginBtn.setOnClickListener {
            val dialogLogin = layoutInflater.inflate(R.layout.dialog_login, null)
            AlertDialog.Builder(this)
                .setTitle("Login Account")
                .setView(dialogLogin)
                .setNegativeButton("Cancel"){dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("Login"){dialog, which ->
                    val username = dialogLogin.findViewById<EditText>(R.id.login_username).text.toString()
                    val password = dialogLogin.findViewById<EditText>(R.id.login_password).text.toString()
                    viewModel.login(username, password)
                }
                .create()
                .show()
        }

        binding.guestBtn.setOnClickListener{
            val dialogGuest = layoutInflater.inflate(R.layout.dialog_guest, null)
            AlertDialog.Builder(this)
                .setTitle("Guest Account")
                .setView(dialogGuest)
                .setNegativeButton("Cancel"){dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("Join Meeting"){dialog, which ->
                    val channelName = dialogGuest.findViewById<EditText>(R.id.guest_channel).text.toString()
                    val rtcToken = dialogGuest.findViewById<EditText>(R.id.guest_token).text.toString()

                    val meetingRoom = MeetingRoom(rtcToken, channelName, "")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("MeetingDetail", meetingRoom)

                    startActivity(intent)
                }
                .create()
                .show()
        }
    }
}