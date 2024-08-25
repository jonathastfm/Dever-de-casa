import random

import matplotlib.pyplot as plt

def simulate_coin_toss(num_tosses):
    heads_count = 0
    results = []

    for _ in range(num_tosses):
        result = random.choice(['heads', 'tails'])
        if result == 'heads':
            heads_count += 1
        results.append(heads_count / (len(results) + 1))

    return results

num_tosses = [100, 1000, 10000]

for tosses in num_tosses:
    results = simulate_coin_toss(tosses)
    plt.plot(range(1, tosses + 1), results, label=f'{tosses} tosses')

plt.xlabel('Number of tosses')
plt.ylabel('Relative frequency of heads')
plt.legend()
plt.show()