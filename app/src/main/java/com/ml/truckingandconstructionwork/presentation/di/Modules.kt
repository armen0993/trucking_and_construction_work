package com.ml.truckingandconstructionwork.presentation.di

import com.ml.truckingandconstructionwork.MainActivityViewModel
import com.ml.truckingandconstructionwork.presentation.ui.add_work.AddWorkViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.forgot_password.ForgotPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.new_password.NewPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.verification_forgot_password.VerificationForgotPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.profile.profile.ProfileViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.checker_client_or_driver.CheckerViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.RegistrationViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.create_pin.CreatePinViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.fingerprint.CreateFingerprintViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.SignInViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details.PersonalDetailsViewModel
import com.ml.truckingandconstructionwork.presentation.ui.settings.SettingsViewModel
import com.ml.truckingandconstructionwork.presentation.ui.add_work.add_offer.AddJobOfferViewModel
import com.ml.truckingandconstructionwork.presentation.ui.main.MainViewModel
import com.ml.truckingandconstructionwork.presentation.ui.offers.OffersListViewModel
import com.ml.truckingandconstructionwork.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainActivityViewModel(get())  }
    viewModel { PersonalDetailsViewModel(get(),get())  }
    viewModel { SplashViewModel(get())  }
    viewModel { AddWorkViewModel()  }
    viewModel { ForgotPasswordViewModel()  }
    viewModel { NewPasswordViewModel()  }
    viewModel { VerificationForgotPasswordViewModel()  }
    viewModel { ProfileViewModel(get())  }
    viewModel { CheckerViewModel()  }
    viewModel { RegistrationViewModel(get(),get())  }
    viewModel { CreatePinViewModel()  }
    viewModel { CreateFingerprintViewModel()  }
    viewModel { SignInViewModel(get(),get())  }
    viewModel { SettingsViewModel(get())  }
    viewModel { AddJobOfferViewModel(get())  }
    viewModel { OffersListViewModel(get())  }
    viewModel { MainViewModel(get())  }
}