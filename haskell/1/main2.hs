overThree :: Integer -> Integer
overThree a = a `div` 3

minusTwo :: Integer -> Integer
minusTwo a = a - 2

getFuel :: Integer -> Integer
getFuel a = minusTwo (overThree a)

recurseFuel total next | next <= 0 = total
                       | next > 0 = recurseFuel (total + next) (getFuel next)

go :: String -> Integer
go line = (recurseFuel 0 (getFuel (read line :: Integer)))

main = do
   content <- readFile "in.txt"
   let readLines = lines content
   let results = map go readLines
   let total = sum results
   putStrLn ("total is:" ++ (show total))
