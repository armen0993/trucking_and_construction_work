package com.ml.truckingandconstructionwork.presentation.di

import com.ml.truckingandconstructionwork.presentation.ui.construction_works.ConstructionWorksViewModel
import com.ml.truckingandconstructionwork.presentation.ui.forgot_password.forgot_password.ForgotPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.forgot_password.new_password.NewPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.forgot_password.verification_forgot_password.VerificationForgotPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.main.profile.ProfileViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.checker_client_or_driver.CheckerViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.create_login_password.CreateLoginPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.create_pin.CreatePinViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.fingerprint.CreateFingerprintViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.main_registration.MainViewModel
import com.ml.truckingandconstructionwork.presentation.ui.registration.personal_details.PersonalDetailsViewModel
import com.ml.truckingandconstructionwork.presentation.ui.settings.SettingsViewModel
import com.ml.truckingandconstructionwork.presentation.ui.splash.SplashViewModel
import com.ml.truckingandconstructionwork.presentation.ui.trucks.TrucksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { PersonalDetailsViewModel(get(),get())  }
    viewModel { SplashViewModel()  }
    viewModel { ConstructionWorksViewModel()  }
    viewModel { ForgotPasswordViewModel()  }
    viewModel { NewPasswordViewModel()  }
    viewModel { VerificationForgotPasswordViewModel()  }
    viewModel { ProfileViewModel()  }
    viewModel { CheckerViewModel()  }
    viewModel { CreateLoginPasswordViewModel(get(),get())  }
    viewModel { CreatePinViewModel()  }
    viewModel { CreateFingerprintViewModel()  }
    viewModel { MainViewModel()  }
    viewModel { SettingsViewModel()  }
    viewModel { TrucksViewModel()  }
}