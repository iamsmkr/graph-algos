# graph-algos

## Test
Check test spec for usage. To execute test suite via sbt, use the following command:
```
$ sbt test
```

## Run
Edges are represented as comma separated list of vertices joined by a tilde like so `<vertex1>~<vertex2>`. To run the app via command line, use following command:
```
$ sbt "run 1~2,1~3,1~4,2~5,2~6,4~7,4~8,5~9,6~10,7~11,8~12,11~12"
```
