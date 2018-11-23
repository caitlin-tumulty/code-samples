#!/usr/bin/env ruby
# CS 430 Module 6 PA
# Caitlin Tumulty

# return an array of all factors of n
def factors (n)
  factors = Array.new(n) { |index| index+=1 }
  return factors.select { |x| n%x == 0}
end

# return an array of all prime numbers less than or equal to n
def primes (n)
  primes = Array.new(n) { |index| index+=1 }
  return primes.select { |x| factors(x).length == 2 }
end

# return an array of all prime factors of n
def prime_factors (n)
  return factors(n).select { |x| factors(x).length == 2 }
end

# return an array of all perfect numbers less than or equal to n
def perfects (n)
  perfects = Array.new
  i = 1
  while i <= n
    temp = factors(i)
    sum = 0
    temp.each do |x|
      sum += x
    end
    if sum == i*2
      perfects.push(i)
    end
    i+=1
  end
  return perfects
end

# return an array of Pythagorean triples whose elements are less than or equal to n
def pythagoreans (n)
  pythagoreans = Array.new
  for i in 1..n
    for h in 1..i
      sum = Math.sqrt(i**2+h**2)
      if sum%1 == 0 && sum <= n
        temp = [i,h,sum.round]
        temp.sort!
        pythagoreans.push(temp)
      end
    end
  end
  pythagoreans.sort!
  return pythagoreans
end
