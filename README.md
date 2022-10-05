# Paypal_CheckOut_Integration

# Dependencies

implementation 'com.paypal.checkout:android-sdk:0.8.1' (add in build.gradle Module Level)

**Add these in your build.gradle (Project Level)**
```ruby
 allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url  "https://cardinalcommerceprod.jfrog.io/artifactory/android"
            credentials {
                // Be sure to add these non-sensitive credentials in order to retrieve dependencies from
                // the private repository.
                username mavenUsername
                password mavenPassword
              } 
          } 
      }
  }
```
**- mavenUsername**

**- mavenPassword**

In your Gradle.properties add Same Username and Password as below
```ruby
mavenUsername=paypal_sgerritz
mavenPassword=AKCp8jQ8tAahqpT5JjZ4FRP2mW7GMoFZ674kGqHmupTesKeAY2G8NcmPKLuTxTGkKjDLRzDUQ
```
Thats all :) 

**Important Notes**

You have to create a Personal Account and a Business account in Paypal.

From your personal account you will pay and in your business account you will get the payment.

**Errors that are handled:**
- inflation error with com.paypal.checkout:android-sdk:0.8.1 
- Could not get unknown property 'paypal_sgerritz' for Credentials [username: null] 
  of type org.gradle.internal.credentials.DefaultPasswordCredentials_Decorated.
