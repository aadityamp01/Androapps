package com.quizapp

import com.quizapp.R
import com.quizapp.Question

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Argentina", "Australia",
            "India", "Austria", 3)

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Angola", "Austria",
            "Armenia", "Australia", 4)

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Belarus", "New Zealand",
            "Brunei", "Brazil", 2
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait", "Belgium",
            "Barbados", "Belize", 1)

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Gabon", "Germany",
            "Fiji", "Finland", 2)

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Germany", "Georgia",
            "Greece", "Fiji", 4)

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Dominica", "Egypt",
            "Denmark", "None Of These", 3)

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Ireland", "Iran",
            "Brazil", "India", 3)

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Australia", "Belgium",
            "Tuvalu", "United States of America", 2)

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Jordan",
            "Sudan", "Palestine", 1)

        questionsList.add(que10)

        return questionsList
    }
}