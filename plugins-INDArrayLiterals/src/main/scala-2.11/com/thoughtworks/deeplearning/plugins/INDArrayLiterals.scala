package com.thoughtworks.deeplearning
package plugins

import com.thoughtworks.deeplearning.DeepLearning.Tape
import com.thoughtworks.raii.asynchronous.Do

import scalaz.concurrent.Future

import org.nd4j.linalg.api.ndarray.INDArray

trait INDArrayLiterals {
  trait ImplicitsApi {
    implicit def indArrayLiteralDeepLearning: DeepLearning.Aux[INDArray, INDArray, INDArray] =
      new DeepLearning[INDArray] {
        override type Data = INDArray
        override type Delta = INDArray

        override def forward(literal: INDArray): Do[Tape[Data, Delta]] = {
          Do.now(Tape(literal, Function.const(Future.now(()))))
        }
      }
  }

  type Implicits <: ImplicitsApi
}
