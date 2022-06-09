# Example Code for Using the SAT Encodings of BroT

This project consists just of a single [Main][main] file with some example code for encoding propositional _atmost_ and _atleast_ constraints with the [FeatureIDE][fide] library.

Further information is given in the [BroT][brot] project and the corresponding paper [_"SAT Encodings of the At-Most-k Constraint: A Case Study on Configuring University Courses"_][paper].

## Setup

This project is a plain Eclipse java project. It uses the libraries in the [`libs`](libs) directory. The SAT encodings are implemented in the [`AtMostSatEncodings`](https://github.com/SoftVarE-Group/BroT/tree/main/Code/EclipseWorkspaces/Development/AtMostSATEncodings) project in BroT. The `AtMostSatEncodings` project was built and included as a jar in the `libs` directory.

[main]: src/de/uulm/brottest/Main.java
[brot]: https://github.com/SoftVarE-Group/BroT
[fide]: https://github.com/FeatureIDE/FeatureIDE
[paper]: https://link.springer.com/chapter/10.1007/978-3-030-30446-1_7
