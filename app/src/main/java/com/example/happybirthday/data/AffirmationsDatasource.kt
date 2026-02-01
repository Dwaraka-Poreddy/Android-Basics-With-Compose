package com.example.happybirthday.data

import com.example.happybirthday.R
import com.example.happybirthday.model.Affirmation

class AffirmationsDatasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.affirmations_image1),
            Affirmation(R.string.affirmation2, R.drawable.affirmations_image2),
            Affirmation(R.string.affirmation3, R.drawable.affirmations_image3),
            Affirmation(R.string.affirmation4, R.drawable.affirmations_image4),
            Affirmation(R.string.affirmation5, R.drawable.affirmations_image5),
            Affirmation(R.string.affirmation6, R.drawable.affirmations_image6),
            Affirmation(R.string.affirmation7, R.drawable.affirmations_image7),
            Affirmation(R.string.affirmation8, R.drawable.affirmations_image8),
            Affirmation(R.string.affirmation9, R.drawable.affirmations_image9),
            Affirmation(R.string.affirmation10, R.drawable.affirmations_image10))
    }
}