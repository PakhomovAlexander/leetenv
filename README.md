# leetenv

[![Run all tests](https://github.com/PakhomovAlexander/leetenv/actions/workflows/run-all-tests.yml/badge.svg?event=push)]
(https://github.com/PakhomovAlexander/leetenv/actions/workflows/run-all-tests.yml)

leetenv is an environment for solving leetcode problems with unit tests, performance tests and pleasure.

### NOTE: this project is under active development, do not use it for a while.

Development roadmap (basic): 
- [ ] `pick <problem-number>` download code template and generate basic module with tests and jmh
- [ ] `test <problem-number` local junit tests
- [ ] `bench <problem-number>` local junit tests
- [ ] `submit <problem-number` remote submit
- [ ] authentication
- [ ] documentation and release

Advanced features:
- [ ] extract code to the library
- [ ] `run <problem-number>` this is a remote run
- [ ] "test case generation v1" that is just several base classes that support input parsing 
- [ ] "test case generation v2" the test case that do not pass is automatically downloaded
- [ ] "test case generation v3" Junit test method is generated automatically with the error case
- [ ] jmh performance comparison with different algorithm versions
- [ ] load tests input generation infrastructure
- [ ] make it CLI application
- [ ] support multi-languages 
- [ ] hints from GPT
- [ ] CI
- [ ] some central statistics server that will run all benchmarks and show statistics among all folks. 


Small todos that can be a good first issue:
- [ ] buildSrc/.../leetenv.java-problem-module.gradle should include jmh plugin but it does not work,
I just add jmg plugin manually to each problem-* module. I believe there is some trick but I don't want
to investigate too much time to it.



