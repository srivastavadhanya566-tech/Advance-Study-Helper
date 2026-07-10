import pandas as pd
from collections import Counter

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
    print()

except FileNotFoundError:
    
    print("\n 'study_metrics.csv' not found. Run your Java program first to log data!\n")