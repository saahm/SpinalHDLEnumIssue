package mylib

import spinal.core._
import spinal.lib._

object MiaoCode {
    def F2_ONE = B"01"
    def F2_TWO = B"10"
}

//Hardware definition
class Bar extends Component {
    val io = new Bundle {
        val myIn = in Bool()
        val myOut = out Bool()
        val myEnOp = in Bits(2 bits)
        val myEO = out Bool()
        val myFOut = out Bool()
    }
    val myFoo = new Foo()
    myFoo.io.myIn := io.myIn
    io.myOut := myFoo.io.myOut
    /*
    *   MiaoEnum.elOne() for the RHS of "->"" mux works, 
    *   mux[MiaoEnum](...) doesnt work
    *   MiaoEnum.elOne doesnt work
    */
    myFoo.io.myEn := io.myEnOp.mux(
        MiaoCode.F2_ONE -> MiaoEnum.elOne,
        MiaoCode.F2_TWO -> MiaoEnum.elTwo,
        default -> MiaoEnum.elTwo
    )
    io.myEO := io.myEnOp.mux(
        MiaoCode.F2_ONE -> True,
        MiaoCode.F2_TWO -> False,
        default -> False
    )
    io.myFOut := myFoo.io.myEO.mux(
        MiaoCode.F2_ONE -> True,
        MiaoCode.F2_TWO -> False,
        default -> False
    )
}