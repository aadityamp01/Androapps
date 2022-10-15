package com.example.tictactoe.model

data class UserModel(
    var uid : String?= "",
    var noOWins : Double? = 0.0,
    var noOLoss : Double? = 0.0,
    var totalMatches : Int? =0
)
