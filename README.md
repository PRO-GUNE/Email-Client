<p align="center">
  <img src="https://github.com/PRO-GUNE/Email-Client/blob/66ee154e2ac4196e5f3fdf9decc55e7899a72026/%F0%9F%93%A7Email_Client.png" alt="Emaill Client Banner"/>
</p>

## 🧐 Project Description
This CLI application allows users to send emails to **Official** and **Personal** users. The application also stores details of all the sent mail and all the saved contacts of the users. The application automatically sends Birthday wishes to personal contacts on their birthdays.

## 💻 Installation
1. Fork and clone this GIT Repository
2. Download the zipped folder of this repository and extract it on your local computer

## 🔗 Dependencies
The following libraries are required for sending the emails via a mail service provider (Gmail used here)
1. javax.mail.jar
2. activation-1.1.1.jar

The dependencies are placed in the `lib` folder.

## 🔍 Key Features
- Add user contacts as Official, Official Friend and Personnal
- Send emails to any contact. The structure of the email will be adjusted automatically according to the type of the contact.
- Automatically send birthday wishes to personal contacts and official friends.
- View all send emails on a given date
- View all contacts that have birthdays on a specified date
- View the total number of contacts in the application

## 📑 Usage
Enter your **email** and **app password** for the mail client app in the `MailService.java` file within the *MailService* class
Run the `Email_Client.java` file and this will open the CLI interface. The available options will be displayed in the CLI interface
### 🧩 Possible options
**1** - Add new recipients - Add new recipients to the program  
- `Official: nimal,nimal@gmail.com,ceo` 
- `Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12`
- `Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10`

**2** - Sending an email - Send an email to the given mail address of the client
- `kamal@gmail.com,<Subject>,<Content>`

**3** - Print out the recipients how have their birthdays on the current date
- `<Date>`

**4** - Printing out details (subject and recipient) of all the emails sent on a date specified by user input
- `<Date>`

**5** - Printing out the number of recipient objects in the application

## 🔮 Optimizations
- Restrict adding duplicate users - using a hash function or hash table
- Use threads to make the process of sending emails non-blocking
## 🧪 Versions
- **v1.0** - This version is a CLI tool only meeting the minimum user requirements specified by the user.
- **v1.1** - This version will have a GUI to interact with the application

## 👨‍💻 Contributions
Chathura Gunasekara @PRO-GUNE
