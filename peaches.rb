

def discard_one_and_take_20_percents(peaches, total_times, left_times)  
  if(left_times == 0)
    return peaches
  end
  
  #TODO extract method
  if(peaches % total_times == 1) 
    return discard_one_and_take_20_percents((peaches - 1) / total_times * (total_times - 1), left_times - 1)
  else
    #TODO raise exception
    return -1
  end
end

left_peaches = 0
not_found = true
total_times = 7
i = 0

while(not_found)
  left_peaches  = discard_one_and_take_20_percents(i, total_times, left_times)
  if(left_peaches > 0)
    not_found = false

    #TODO extract output method
    puts "sum: #{i}"
    puts "left: #{left_peaches}"
    
  end
  
  #TODO increase by (total_times - 1)
  i = i + 1
end

#TODO add test case