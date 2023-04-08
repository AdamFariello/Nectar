import requests
from bs4 import BeautifulSoup

# Empty list to hold urls
url = []

# Open input.txt file and add urls to list
inputFile = open("input.txt", "r")
url = inputFile.readlines()

# Close file reader
inputFile.close()

# print("First URL:")
link = url[0]

# header = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36'}

header = {
    'User-Agent': ('Mozilla/5.0 (Windows NT 10.0; Win64; x64)'
                    'AppleWebKit/537.36 (KHTML, like Gecko)'
                    'Chrome/111.0.0.0 Safari/537.36'),
    'Accept-Language': 'en-US, en;q=0.5'
}

page = requests.get(link, headers=header)
# print(page.text)

soup = BeautifulSoup(page.text, features="html.parser")
productTitle = soup.find('span', {'id':'productTitle'}).text.strip()
print("Product Title: " + productTitle)

# Print list
# print(url)