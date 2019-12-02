overThree :: Integer -> Integer
overThree a = a `div` 3

minusTwo :: Integer -> Integer
minusTwo a = a - 2

getFuel :: Integer -> Integer
getFuel a = minusTwo (overThree a)

go :: String -> Integer
go line = (getFuel (read line :: Integer))

main = do
   content <- readFile "in.txt"
   let readLines = lines content
   let results = map go readLines
   let total = sum results
   putStrLn ("total is:" ++ (show total))