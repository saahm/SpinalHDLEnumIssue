package mylib

import spinal.core._
import spinal.lib._

object MiaoEnum extends SpinalEnum {
    val elOne, elTwo = newElement()
}

class Foo extends Component {
    val io = new Bundle {
        val myIn = in Bool()
        val myOut = out Bool()
        val myEn = in(MiaoEnum())
        val myEO = out Bits(2 bits)
    }
    val myReg = RegNext(io.myIn)
    val myEnReg = Reg(MiaoEnum()) init(MiaoEnum.elOne)
    when(myEnReg === MiaoEnum.elOne){
        io.myEO := B"01"
    }.otherwise{
        io.myEO := B"10"
    }
}
