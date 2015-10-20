package de.andrena.testing.tools.endtoend.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecommendationPage {
	
	@FindBy(id = "image")
	private WebElement image;
	
	public RecommendationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public boolean imageIsNotLoadingImage() {
		return !getImageSource().contains("loading");
	}

	private String getImageSource() {
		return image.getAttribute("src");
	}

}
