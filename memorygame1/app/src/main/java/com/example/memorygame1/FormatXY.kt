package com.example.memorygame1

import java.io.Serializable

class FormatXY: Serializable{
    var x: Int
    var y: Int

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}