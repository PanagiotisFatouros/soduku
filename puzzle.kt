class puzzle {

    private fun printStuff(puzzle: Array<Array<Int>>) {
        println("\n")
        for (index in 0..8){
            for (i in 0..8) {
                print(puzzle[index][i].toString() + " ")
                if ((i + 1) % 3 == 0) print("  ")
            }
            print("\n")
            if ((index + 1) % 3 == 0) print("\n")
        }
        println("\n")
    }

    fun solve(puzzle: Array<Array<Int>>, start_i: Int, start_j: Int) {
        var j_start = start_j
        for (i in start_i..8) {
            if (i > start_i) j_start = 0
            for (j in (j_start % 9)..8) {
                if (puzzle[i][j].equals(0)){
                    for (num in 1..9) {
                        if(checkSquare(i, j, num, puzzle)) {
                            puzzle[i][j] = num
                            solve(puzzle, start_i + Math.max(0, j - 7), j + 1)
                            puzzle[i][j] = 0
                        }
                    }
                    if (puzzle[i][j].equals(0)) {
                        return
                    }
                }
            }
        }
        printStuff(puzzle)
    }

   fun checkSquare (i: Int, j: Int, num: Int, puzzle: Array<Array<Int>>): Boolean {
        for (y in 0..8) {
            if (puzzle[i][y].equals(num)) {
                return false
            }
        }
        for (x in 0..8) {
            if (puzzle[x][j].equals(num)) {
                return false
            }
        }
        for (movement_x in 0..2) {
            for (movement_y in 0..2) {
                if (puzzle[i - i % 3 + movement_x][j - j % 3 + movement_y].equals(num)) {
                    return false
                }
            }
        }
        return true
    }
}

fun main() {
     val puzzle = arrayOf(
                        arrayOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
                        arrayOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
                        arrayOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
                        arrayOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
                        arrayOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
                        arrayOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
                        arrayOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
                        arrayOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
                        arrayOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
                        )
    puzzle().solve(puzzle, 0, 0)
}