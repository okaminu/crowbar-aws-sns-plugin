![Alt text](logo.jpg?raw=true)
# Crowbar AWS SNS Plugin
Crowbar library plugin, which uses Amazon SNS service as message provider, to deliver a confirmation code via SMS.  

* Crowbar library for user authentication is provided as [a separate project](https://github.com/tlistas/Crowbar).
* Crowbar API is also provided as [a separate project](https://github.com/tlistas/Crowbar_API).

### Download

### Usage
* The plugin is a runtime dependency for a [Crowbar library](https://github.com/tlistas/Crowbar). 
* It uses AWS SDK for Java and AWS credentials have to be supplied as env. variables, as ~/.aws/credentials file or via other way described in [AWS Credentials guide](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html).

If credentials are provided as env. variables, set:
```
AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
AWS_REGION
```
* Provided mobile number has to be in the E.164 format.  For example, a U.S. phone number in E.164 format would appear as +1XXX5550100.
* Set SMS preferences for you AWS account according to [AWS documentation](https://docs.aws.amazon.com/sns/latest/dg/SMSMessages.html)
### License

