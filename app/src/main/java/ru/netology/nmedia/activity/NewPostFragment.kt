package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.netology.nmedia.databinding.FragmentNewPostBinding

class NewPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewPostBinding.inflate(layoutInflater, container, false)

        val text = activity?.intent?.getStringExtra(Intent.EXTRA_TEXT)
        binding.editContent.setText(text)

        binding.buttonOk.setOnClickListener {
            val text = binding.editContent.text.toString()
            if (text.isBlank()) {
                activity?.setResult(Activity.RESULT_CANCELED)
            } else {
                activity?.setResult(
                    Activity.RESULT_OK,
                    Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
            }
            activity?.finish()
        }
        return binding.root
    }
}



