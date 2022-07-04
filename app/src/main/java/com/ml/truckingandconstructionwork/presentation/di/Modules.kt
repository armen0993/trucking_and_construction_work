package com.ml.truckingandconstructionwork.presentation.di

import com.ml.truckingandconstructionwork.presentation.ui.special_equipment.SpecialEquipmentViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.forgot_password.ForgotPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.new_password.NewPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.forgot_password.verification_forgot_password.VerificationForgotPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.main.profile.ProfileViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.checker_client_or_driver.CheckerViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.create_login_password.CreateLoginPasswordViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.create_pin.CreatePinViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.fingerprint.CreateFingerprintViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.MainViewModel
import com.ml.truckingandconstructionwork.presentation.ui.sign_in.registration.personal_details.PersonalDetailsViewModel
import com.ml.truckingandconstructionwork.presentation.ui.settings.SettingsViewModel
import com.ml.truckingandconstructionwork.presentation.ui.job_offer.JobOfferViewModel
import com.ml.truckingandconstructionwork.presentation.ui.job_offer.add_offer.AddJobOfferViewModel
import com.ml.truckingandconstructionwork.presentation.ui.job_offer.offers_list.OffersListViewModel
import com.ml.truckingandconstructionwork.presentation.ui.splash.SplashViewModel
import com.ml.truckingandconstructionwork.presentation.ui.trucks.TrucksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { PersonalDetailsViewModel(get(),get())  }
    viewModel { SplashViewModel()  }
    viewModel { SpecialEquipmentViewModel()  }
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
    viewModel { JobOfferViewModel()  }
    viewModel { AddJobOfferViewModel()  }
    viewModel { OffersListViewModel()  }
}