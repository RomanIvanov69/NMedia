package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях",
            published = "21 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 50,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            published = "22 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 1500,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Нетология - это образовательная диджитал-платформа, включающая механизм рекомендации учебных программ с участием искусственного интеллекта.",
            published = "18 сентября в 20:15",
            likedByMe = false,
            likeCount = 0,
            shareCount = 1_000_000,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Меняйтесь с нами — начните с бесплатных лекций и курсов → http://netolo.gy/fEU",
            published = "20 мая в 19:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            published = "19 мая в 15:45",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Собеседование часто похоже на первое свидание: мы хотим показать себя с лучшей стороны, волнуемся и переживаем, что что-то пойдёт не так. Для тех, кто решил начать новый год с новой работы, рассказываем, как подготовиться к собеседованию",
            published = "18 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Как выстроить работу удалённо, чтобы ничего не сломалось, разобраться в языках программирования и найти себя в мире Data Science? Ответы ищите на наших бесплатных занятиях.",
            published = "17 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Главный вопрос программиста-новичка — какой язык выбрать для изучения? Откроем секрет, каждый язык программирования открывает перед разработчиком разные возможности. Поэтому к выбору стоит подойти серьёзно.",
            published = "16 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " Сколько раз вы пытались выучить английский, разобраться с Photoshop или освоить технику печати со скоростью тысяча символов в наносекунду? Любая учёба — объёмный и долгий процесс, который так и тянет прервать где-то на середине. Разбираемся, как организовать самостоятельное обучение, чтобы прийти к заветной цели",
            published = "15 мая в 18:36",
            likedByMe = false,
            likeCount = 0,
            shareCount = 0,
            lookCount = 0
        ),
    ).reversed()

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else if (it.id == id && it.likedByMe) {
                it.copy(likeCount = it.likeCount - 1, likedByMe = !it.likedByMe)
            } else {
                it.copy(
                    likedByMe = !it.likedByMe,
                    likeCount = it.likeCount + 1
                )
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun eyes(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(lookCount = it.lookCount + 1)
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shareCount = it.shareCount + 1)
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Roman",
                    likedByMe = false,
                    published = "now"
                )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }
}
