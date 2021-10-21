package mylib

import spinal.core._
import spinal.sim._
import spinal.core.sim._

import scala.util.Random


//MyTopLevel's testbench
object MyBarSim {
  def main(args: Array[String]) {
    SimConfig.withWave.doSim(new Bar){dut =>
      //Fork a process to generate the reset and the clock on the dut
      dut.clockDomain.forkStimulus(period = 10)
      
      //Wait a rising edge on the clock
      dut.clockDomain.waitRisingEdge()

    }
  }
}
