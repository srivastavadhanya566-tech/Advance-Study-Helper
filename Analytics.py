import pandas as pd
from collections import Counter
import matplotlib.pyplot as plt

try:
    
    df = pd.read_csv('study_metrics.csv', names=['Folder', 'KeywordsString'])
    
    
    all_keywords = []
    for keywords_str in df['KeywordsString'].dropna():
        words = keywords_str.split()
        all_keywords.extend(words)
        

    keyword_counts = Counter(all_keywords)
    
    
    metrics_df = pd.DataFrame(keyword_counts.items(), columns=['Keyword', 'Frequency'])
    

    metrics_df = metrics_df.sort_values(by='Frequency', ascending=False).reset_index(drop=True)
    
    
    print("\n Your Top Studied Topics & Keywords:")
    print(metrics_df.head(10))

    
    metrics_df.head(10).plot(x='Keyword', y='Frequency', kind='bar', color='purple')
    plt.title('Top Studied Topics & Keywords')
    plt.xlabel('Keywords')
    plt.ylabel('Frequency')
    plt.xticks(rotation=45) 
    plt.tight_layout()      
    plt.show()

except FileNotFoundError:
    print("\n 'study_metrics.csv' not found. Run your Java program first to log data!\n")