function createObj(username, password) {
    this.username = username
    this.password = password
}

// console.log("list of objects:")
const array_objet = [];

const fs = require("fs")
const readline = require("readline")

const file = readline.createInterface({
    input: fs.createReadStream("cases.txt")
})

file.on("line", (data) => {
    console.log("line: ", data)
    var stringData = data.toString().split(" ")
    const object = new createObj(stringData[0], stringData[1])
    console.log(object)
    array_objet.push(object)
})

/*
fs.readFile("cases.txt", (error, data) => {
    if(error)
        throw error
    console.log("line: " + data.toString())
    
    var stringData = data.toString().split(" ")
    const object = new createObj(stringData[0], stringData[1])
    console.log(object)
    // array_objet.push(object)
    
})
*/

console.log(array_objet)