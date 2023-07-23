# Aadhar_based_EHRS
### Existing solutions to store patient records in the industry
It is often observed that a lot of doctors use electronic health record systems to monitor their patients. This ends up saving a lot of paper as there is no need to maintain files physically. However, this also makes sharing of records between doctors difficult.

### Introduction
Aadhar based EHR system gives doctors all over the country a common platform to manage and share their patient health records. Moreover, this also allows national health agencies to track frequency of cases of diseases as well as availability of doctors in all regions of the country.

### How it works
<p>For a patient, they are allowed to register an account after verifying their Aadhar card number(Personal identity number in India) to prevent account duplication. Once the user is registered on the system, they can visit any doctor in the country and allow them access to their health records by means of OTP verification.</p>
<p>Once the doctor has access to a patient's records, they can view the entire medical history of the patient. This improves their diagnostic ability. The doctor can make changes to the patient's timeline and add a new record as per requirement.</p>
<p>The patient can revoke the access of the doctor to his records at any time. The doctor too, can end the access once the treatment is concluded.</p>
<p>The third user in this system are health agencies who can use the data collected through this system to manage and monitor cases in the country. This allows better allocation and management of resources required to aid and alleviate epidemic like situations frequently seen during the COVID-19 outbreak. The data collected through this system would make it much easier to track all kinds of diseases. Furthermore, there will also be data on the availability of doctors in various geographical regions.</p>
<p>The health agencies will not have access to individual patient data, only the statistics. There is no unauthorised access to critical patient information in the entire system.</p>

### How to use it
<p>This project is created entirely using Jakarta EE 8. The client side application is made using Java Server Faces and the server-side application is made using a combination of EJBs, JPA and servlets/filters. The security is implemented using Java EE 8 Security.</p>
<p>To execute and run this project, you will need a Java Runtime Environment(JRE) which can run Java SE 8.</p>
<p>You also need a web server which can execute a Java web application.(Apache TomCat, Payara Server etc.)</p>
<p>It runs on all modern web browsers, but has been repeatedly tested on Google Chrome and Microsoft Edge.</p>
<p>This project has been created with India in mind, but you are free to modify this project to suit your country by swapping out the Aadhar number with your home country's identification.</p>


