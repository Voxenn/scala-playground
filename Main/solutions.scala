/**
Merge Sorted Array
https://leetcode.com/problems/merge-sorted-array/
*/
import scala.util.Sorting.quickSort
object Solution {
    def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
        var c = 0
        val max = n + m
        for(i <- m.until(max)) {
            nums1(i) = nums2(c)
            c += 1
        }
        quickSort(nums1)
    }
}

/**
Contains duplicate
https://leetcode.com/problems/contains-duplicate/submissions/
*/
import scala.collection.mutable.Set

object Solution {
    def containsDuplicate(nums: Array[Int]): Boolean = {
        var numSet: scala.collection.mutable.Set[Int] = Set()
        nums.foreach(num => { if(numSet.add(num) == false) return true })
        false
    }
}

/**
Intersection of two arrays
https://leetcode.com/problems/intersection-of-two-arrays-ii/submissions/
*/
import scala.collection.mutable.ListBuffer

object Solution {
    def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
        var numBuff = new ListBuffer[Int]()
        var count = 0
        nums1.toList.collect{
            case num if nums2.contains(num) => 
                count = nums1.count(_ == num).min(nums2.count(_ == num))
                if(numBuff.contains(num) != true) {
                    numBuff += num
                } else if((numBuff.count(_ == num) < count))
                    numBuff += num
        }
        numBuff.toArray
    }
}

/**
Jump Game II
https://leetcode.com/problems/jump-game-ii/submissions/
*/
//the maximum number of jumps is a cumulative number of how many jumps were performed
//e.g. [1, 3, 1, 4, 2, 0, 1]
//this would equate to 3
//You jump once from index 0 to 1
//You jump twice from index 1 to 4
//

object Solution {
    def jump(nums: Array[Int]): Int = {
        //store the amount of jumps needed to reach the end
        val goal = nums.length - 1
        var count = 0
        var maxCount = 0
        var jumps = 0
        nums.zipWithIndex.foreach(num => {
            // base case
            if(num._2 == goal) return jumps
            //check the max jump we can take right now
            count = math.max(count, (num._1 + num._2))
            //if the current index reaches the goal, return jumps + 1
            if (num._2 >= goal) return jumps + 1
            //at this point, check to see if we can't go further. 
            //If so, set the max count and increment the jumps
            if(num._2 == maxCount) {
                maxCount = count
                jumps += 1
            }
        })
        jumps
    }
}