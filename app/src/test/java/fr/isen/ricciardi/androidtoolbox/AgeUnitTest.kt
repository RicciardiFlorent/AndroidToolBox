package fr.isen.ricciardi.androidtoolbox

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat

class AgeUnitTest{

    fun setup(): FormActivity{
        val sut = FormActivity()
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        sut.currentDate = formatter.parse("28/01/2020")
        return sut
    }
    @Test
    fun  simpleYear_test() { //test for the year
        val sut = setup()
        Assert.assertEquals(20, sut.getAge(2000,1,1))
    }

    @Test
    fun simpleMonth_test(){ //test for the month
        val sut = setup()
        Assert.assertEquals(19, sut.getAge(2000,2,1))
    }


    @Test
    fun simpleDay_test(){ //test for the day
        val sut = FormActivity()
        Assert.assertEquals(20, sut.getAge(2000,1,29))
    }

}