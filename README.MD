![Alt text](crowbar-logo.jpg?raw=true) ![Alt text](aws-logo.png?raw=true)
# Crowbar AWS SNS Plugin
Crowbar library plugin, which uses Amazon SNS service as message provider, to deliver a confirmation code via SMS.  


### Download

### Usage
* The plugin implements *ConfirmationMessageGateway* from Crowbar API and can be easily plugged in into the application or swapped with another plugin.

```
class YourClass(private val confirmationMessageGateway: ConfirmationMessageGateway) {

    fun yourMethod(address: String, message: String) {
        confirmationMessageGateway.send(address, message)
    }
}
```
* It uses AWS SDK for Java and AWS credentials have to be supplied as env. variables, ~/.aws/credentials file or via other way described in [AWS Credentials guide](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html).

If credentials are provided as env. variables, set:
```
AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
AWS_REGION
```
* Provided mobile number has to be in the E.164 format.  For example, a U.S. phone number in E.164 format would appear as +1XXX5550100.
* Set SMS preferences for you AWS account according to [AWS documentation](https://docs.aws.amazon.com/sns/latest/dg/SMSMessages.html)

### License

This library is licensed under MIT. Full license text is available in [LICENSE](https://github.com/tlistas/Crowbar_AWS_SNS_Plugin/blob/TLIST-466-mobile-confirmation/LICENSE.txt).
