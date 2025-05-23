class AccountUpdater {

    def updateAccountDetails(Map accountData)
        if (!accountData.accountNumber) {
            throw new IllegalArgumentException("Account number is required")
        }

        println "Updating account: ${accountData.accountNumber}"
        println "New Holder Name: ${accountData.holderName}"
        println "New Balance: ${accountData.balance}"

        return "Account ${accountData.accountNumber} updated successfully."
    }
}

// Sample execution (if run directly)
if (this == null || this.class.name == 'Script1') {
    def accountService = new AccountUpdater()
    def result = accountService.updateAccountDetails([
            accountNumber: "123456789",
            holderName   : "John Doe",
            balance      : 5000.75
    ])
    println result
}