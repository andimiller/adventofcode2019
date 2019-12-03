name := "adventofcode2019"

version := "0.1"

scalaVersion := "2.12.10"

scalacOptions += "-Ypartial-unification"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0"
libraryDependencies += "io.higherkindness" %% "droste-core" % "0.8.0"
libraryDependencies += "org.typelevel" %% "cats-mtl-core" % "0.7.0"
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
