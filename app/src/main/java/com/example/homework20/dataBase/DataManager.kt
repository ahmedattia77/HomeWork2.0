package com.example.homework20.dataBase

import com.example.homework20.R
import com.example.homework20.models.VacationModel

object DataManager{

    fun getData () :List<VacationModel> {
        var vList = mutableListOf<VacationModel>(
            VacationModel(image = R.drawable.picture_1, title = "Coeurdes Alpes" , review = "355 Review"),
            VacationModel(image = R.drawable.picture_2, title = "Coeurdes Alpes" , review = "268 Review"),
            VacationModel(image = R.drawable.picture_3, title = "Coeurdes Alpes" , review = "354 Review"),
            VacationModel(image = R.drawable.picture_1, title = "Coeurdes Alpes" , review = "355 Review"),
            VacationModel(image = R.drawable.picture_2, title = "Coeurdes Alpes" , review = "268 Review"),
            VacationModel(image = R.drawable.picture_3, title = "Coeurdes Alpes" , review = "355 Review")
        )
        return vList
    }

}