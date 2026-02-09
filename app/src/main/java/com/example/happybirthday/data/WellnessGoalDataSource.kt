package com.example.happybirthday.data

import com.example.happybirthday.R
import com.example.happybirthday.model.Affirmation
import com.example.happybirthday.model.WellnessGoal

class WellnessGoalDataSource() {
    fun loadWellnessGoals(): List<WellnessGoal> {
        return listOf<WellnessGoal>(
            WellnessGoal(R.string.wellness_summary1, R.string.wellness_description1, R.drawable.affirmations_image1),
            WellnessGoal(R.string.wellness_summary2, R.string.wellness_description2, R.drawable.affirmations_image2),
            WellnessGoal(R.string.wellness_summary3, R.string.wellness_description3, R.drawable.affirmations_image3),
            WellnessGoal(R.string.wellness_summary4, R.string.wellness_description4, R.drawable.affirmations_image4),
            WellnessGoal(R.string.wellness_summary5, R.string.wellness_description5, R.drawable.affirmations_image5),
            WellnessGoal(R.string.wellness_summary6, R.string.wellness_description6, R.drawable.affirmations_image6),
            WellnessGoal(R.string.wellness_summary7, R.string.wellness_description7, R.drawable.affirmations_image7),
            WellnessGoal(R.string.wellness_summary8, R.string.wellness_description8, R.drawable.affirmations_image8),
            WellnessGoal(R.string.wellness_summary9, R.string.wellness_description9, R.drawable.affirmations_image9),
            WellnessGoal(R.string.wellness_summary10, R.string.wellness_description10, R.drawable.affirmations_image10)
        )
    }
}