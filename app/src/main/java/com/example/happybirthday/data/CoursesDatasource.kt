package com.example.happybirthday.data

import com.example.happybirthday.R
import com.example.happybirthday.model.Topic

class CoursesDatasource {
    object DataSource {
        val topics = listOf(
            Topic(R.string.c_architecture, 58, R.drawable.c_architecture),
            Topic(R.string.c_crafts, 121, R.drawable.c_crafts),
            Topic(R.string.c_business, 78, R.drawable.c_business),
            Topic(R.string.c_culinary, 118, R.drawable.c_culinary),
            Topic(R.string.c_design, 423, R.drawable.c_design),
            Topic(R.string.c_fashion, 92, R.drawable.c_fashion),
            Topic(R.string.c_film, 165, R.drawable.c_film),
            Topic(R.string.c_gaming, 164, R.drawable.c_gaming),
            Topic(R.string.c_drawing, 326, R.drawable.c_drawing),
            Topic(R.string.c_lifestyle, 305, R.drawable.c_lifestyle),
            Topic(R.string.c_music, 212, R.drawable.c_music),
            Topic(R.string.c_painting, 172, R.drawable.c_painting),
            Topic(R.string.c_photography, 321, R.drawable.c_photography),
            Topic(R.string.c_tech, 118, R.drawable.c_tech)
        )
    }
}