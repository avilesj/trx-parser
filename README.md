# trx-classifier

App to consolidate csv exports from financial institutions into a single one for personal finances purposes.

# Usage

```bash
$ docker build -t trx-classifier .
$ docker run -v .:/app trx-classifier
```

By default it will read the bank configurations from "banks/" and will grab all csvs from "trx/". The output file is "output.csv"
