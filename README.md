This is a project in the course Software Testing, Verification and Validation at the University of Porto, where I did exchange studies in the autumn of 2024. I was given a game developed in Java and tasked to test it as thoroughly as possible according to a few criteria. The main objectives of the tests were to achieve the highest possible branch coverage and mutation score, using any of the multiple techniques learned in the course. Since the source code could not be changed at all, challenges included reaching private methods and branches dependent on inner states, as well as finding useful assertions for some of the void methods. In the end, 629 of 638 branches were covered while time constraints meant that the number of killed mutants stopped at 619 out of 1127.

Key features:
- JUnit test suite for high branch coverage and mutation coverage.
- Mockito framework used to imitate behaviour, isolate methods and find side effects.
- Parameterised tests to systematically and efficiently validate multiple scenarios.
