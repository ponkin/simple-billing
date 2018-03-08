# Run
```bash
gradlew run
```

# Build
```bash
gradlew build
```

# API

## create account
```bash
curl -v -XPOST http://localhost:8000/accounts
```

## get account info
```bash
curl -v http://localhost:8000/accounts/{accId}
```

## make transfer
```bash
curl -v -XPOST -H 'Content-Type: application/json' http://localhost:8000/transfers -d '{"from": 0, "to": 1, "amount": 500}'
```

## default account
There is default account with id=0. see [init_accounts.sql](src/main/resources/init_accounts.sql) with initial amount of money.