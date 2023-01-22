package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.roundToInt
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        )
        val printHearts: TextView = findViewById(R.id.heartTextView)
        val printShares: TextView = findViewById((R.id.shareTextView))
        val printEyes: TextView = findViewById(R.id.eyesTextView)

        with(binding)
        {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            heart.setOnClickListener {
                if (it !is ImageButton) {
                    return@setOnClickListener
                }
                if (!post.likedByMe) {
                    post.likeCount++
                    printHearts.text = clickCount(post.likeCount)
                    post.likedByMe = !post.likedByMe
                    heart.setImageResource(R.drawable.red_heart)
                } else {
                    post.likeCount--
                    printHearts.text = clickCount(post.likeCount)
                    heart.setImageResource(R.drawable.baseline_favorite_border_24)
                    post.likedByMe = !post.likedByMe
                }
            }
            share.setOnClickListener {
                if (it !is ImageButton) {
                    return@setOnClickListener
                }
                post.shareCount++
                printShares.text = clickCount(post.shareCount)
            }
            eyes.setOnClickListener {
                if (it !is ImageButton) {
                    return@setOnClickListener
                }
                post.lookCount++
                printEyes.text = clickCount(post.lookCount)
            }
        }
    }

    private fun clickCount(count: Int): String {
        return when (count) {
            in 0..999 -> count.toString()
            in 1000..1_099 -> {
                (count / 1000).toString() + "K"
            }
            in 1100..9_999 -> {
                var text = (count.toDouble() / 1000)
                text = (text * 10).roundToInt() / 10.0
                text.toString() + "K"
            }
            in 10_000..999_999 -> {
                val text = (count / 1000)
                text.toString() + "K"
            }
            in 1_000_000..1_099_999 -> {
                val text = (count / 1_000_000)
                text.toString() + "M"
            }
            else -> {
                var text = (count.toDouble() / 1_000_000)
                text = (text * 10).roundToInt() / 10.0
                text.toString() + "M"
            }
        }
    }
}

