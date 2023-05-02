from bs4 import BeautifulSoup
import requests

def get_asin(soup):
    try:
        ASIN = soup.find('link', {'rel': 'canonical'})['href']
        ASIN = ASIN[len(ASIN) - 10: len(ASIN) - 1]

    except AttributeError:
        ASIN = ""

    return ASIN

# Function to extract Product Title
def get_title(soup):

    try:
        # Outer Tag Object
        title = soup.find("span", {"id": 'productTitle'})

        # Inner NavigableString Object
        title_value = title.string

        # Title as a string value
        title_string = title_value.strip()

        # # Printing types of values for efficient understanding
        # print(type(title))
        # print(type(title_value))
        # print(type(title_string))
        # print()

    except AttributeError:
        title_string = ""

    return title_string

# Function to extract Product Price
def get_price(soup):

    try:
        price = soup.find(
            "span", {'class': 'a-offscreen'}).string.strip()

    except AttributeError:
        price = ""

    return price

# Function to extract Product Rating
def get_rating(soup):

    try:
        rating = soup.find("i", {'class': 'a-icon a-icon-star a-star-4-5'}).string.strip()

    except AttributeError:

        try:
            rating = soup.find("span", {'class': 'a-icon-alt'}).string.strip()
        except:
            rating = ""

    return rating

# Function to extract Number of User Reviews
def get_review_count(soup):
    try:
        review_count = soup.find(
            "span", {'id': 'acrCustomerReviewText'}).string.strip()

    except AttributeError:
        review_count = ""

    return review_count

# Function to extract Availability Status
def get_availability(soup):
    try:
        available = soup.find("div", {'id': 'availability'})
        available = available.find("span").string.strip()

    except AttributeError:
        available = ""

    return available


if __name__ == '__main__':

    # Headers for request
    HEADERS = ({'User-Agent':
                'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36',
                'Accept-Language': 'en-US, en;q=0.5'})

    inputFile = open("input.txt", "r")
    LINKS = inputFile.readlines()
    inputFile.close()


    outputFile = open("output.txt", "w")
    for i in range(len(LINKS)):
        URL = LINKS[i]

        # HTTP Request
        webpage = requests.get(URL, headers=HEADERS)

        # Soup Object containing all data
        soup = BeautifulSoup(webpage.text, "html.parser")

        # Function calls to display all necessary product information
        print()
        print("Product ASIN =", get_asin(soup))
        print("Product Title =", get_title(soup))
        print("Product Price =", get_price(soup))
        print("Product Rating =", get_rating(soup))
        print("Number of Product Reviews =", get_review_count(soup))
        print("Availability =", get_availability(soup))
        print()
        
        # Writes to output.txt
        outputFile.write("Product ASIN = " + get_asin(soup))
        outputFile.write("\nProduct Title = "+ get_title(soup))
        outputFile.write("\nProduct Price = "+ get_price(soup))
        outputFile.write("\nProduct Rating = "+ get_rating(soup))
        outputFile.write("\nNumber of Product Reviews = "+ get_review_count(soup))
        outputFile.write("\nAvailability = "+ get_availability(soup))
        outputFile.write("\n\n")
    outputFile.close()


