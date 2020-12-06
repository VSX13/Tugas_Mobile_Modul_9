package com.example.aplikasipemesanan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewPesananActivity : AppCompatActivity() {
    private lateinit var editPesananView: EditText
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pesanan)
        editPesananView = findViewById(R.id.edit_pesanan)
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPesananView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editPesananView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
    companion object {
        const val EXTRA_REPLY =
            "com.example.android.pesananlistsql.REPLY"
    }
}