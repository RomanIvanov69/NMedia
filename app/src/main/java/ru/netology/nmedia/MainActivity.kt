package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
    }

    val post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология\n" +
                "        начиналась с интенсивов по онлайн-маркетингу. Затем\n" +
                "        появились курсы по дизайну, разработке, аналитике\n" +
                "        и управлению. Мы растём сами и помогаем расти\n" +
                "        студентам: от новичков до уверенных профессионалов.\n" +
                "        Но самое важное остаётся с нами: мы верим, что в\n" +
                "        каждом уже есть сила, которая заставляет хотеть\n" +
                "        больше, целиться выше, бежать быстрее. Наша миссия\n" +
                "        - помочь встать на путь роста и начать цепочку\n" +
                "        перемен -> http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMe = false,
        likeCount = 0,
        shareCount = 0,
        lookCount = 0
    )
}
