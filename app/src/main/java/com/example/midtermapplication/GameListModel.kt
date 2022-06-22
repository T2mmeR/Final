package com.example.midtermapplication

class GameListModel {

    var count = 0
    var next = ""
    var previous = null
    lateinit var results: MutableList<Result>

    class Result{

        var id=0
        var name =""
        var released = ""
        var background_image = ""
        var rating_top = 0

    }

}