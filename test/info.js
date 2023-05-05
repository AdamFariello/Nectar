function createObj(username, password) {
    this.username = username
    this.password = password
}

const fs = require("fs")
fs.readFile("cases.txt", (error, data) => {
    if(error)
        throw error
    console.log(data.toString())
})

console.log("list of objects:")