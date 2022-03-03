#import "RnHelpScout.h"
#import "Beacon.h"
#import <React/RCTBridgeModule.h>
#import <React/RCTConvert.h>

@implementation RnHelpScout

RCT_EXPORT_MODULE()

// Example method
// See // https://reactnative.dev/docs/native-modules-ios
NSString *helpscoutBeaconID;
HSBeaconUser *beaconUser;

RCT_EXPORT_METHOD(init:(NSString *)beaconID)
{
    if (beaconID == nil) {
        NSLog(@"[init] missing parameter: beaconID");
        return;
    }

    helpscoutBeaconID = beaconID;
}

RCT_EXPORT_METHOD(open)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        HSBeaconSettings *settings = [[HSBeaconSettings alloc] initWithBeaconId:helpscoutBeaconID];
        [HSBeacon openBeacon:settings];
    });
}

RCT_EXPORT_METHOD(identify:(NSDictionary *)identity)
{
    HSBeaconUser *user = [[HSBeaconUser alloc] init];

    if ([identity objectForKey:@"email"] != NULL) {
        user.email = [RCTConvert NSString:identity[@"email"]];
    }

    if ([identity objectForKey:@"name"] != NULL) {
        user.name = [RCTConvert NSString:identity[@"name"]];
    }
    
    for (NSString *key in identity) {
        if ([key isEqual:@"email"] || [key isEqual:@"name"]) continue;
        id value = identity[key];
        [user addAttributeWithKey:key value:[RCTConvert NSString:value]];
    }
    
    [HSBeacon login:user];
}




@end
