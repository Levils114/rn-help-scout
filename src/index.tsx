import { NativeModules, Platform } from 'react-native';

interface RNHelpScoutIdentifyProps{
  name: string;
  email: string;
}

interface RNHelpScoutProps{
  init(beaconID: string): void;
  open(): void;
  contactForm(): void;
  identify({ name, email }: RNHelpScoutIdentifyProps): void;
}

const LINKING_ERROR =
  `The package 'rn-help-scout' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const RnHelpScout: RNHelpScoutProps = NativeModules.RnHelpScout
  ? NativeModules.RnHelpScout
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export default RnHelpScout;