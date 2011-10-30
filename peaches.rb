def discard_one_and_take_20_percents(peaches, left_times)  
  if(left_times == 0)
    return peaches
  end
  
  if(peaches % 5 == 1) 
    return discard_one_and_take_20_percents((peaches - 1) / 5 * 4, left_times - 1)
  else
    return -1
  end
end

left_times = 5
left_peaches = 0

not_found = true
i = 0

while(not_found)
  left_peaches  = discard_one_and_take_20_percents(i ,left_times)
  if(left_peaches > 0)
    not_found = false
    puts "sum: #{i}"
    puts "left: #{left_peaches}"
  end
  i = i + 1
end