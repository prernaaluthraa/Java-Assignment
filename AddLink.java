    try:
        time.sleep(2)
        #in_interestrate = '0.0125'
        interestrate_element = driver.find_element(By.XPATH, '/html/body/div/div/main/div/div[2]/div/div[2]/div/div/div[3]/form/div[1]/div/div[2]/div/div[2]/div[2]/div[1]/div/div/div/div/table/tbody/tr[1]/td[2]/div/div/div/span/input')
        print("interestrate field to be updated",obj.interest_rate_reduction_increments)
        obj.printlog.info(f"interestrate field to be updated",obj.interest_rate_reduction_increments)

        interestrate_element.send_keys(obj.interest_rate_reduction_increments)
        print("interestrate_element updated.", obj.interest_rate_reduction_increments)    
        obj.printlog.info(f"interestrate_element updated." , obj.interest_rate_reduction_increments)
    except (TimeoutException, StaleElementReferenceException) as e:
        traceback.print_exc()
        print(f"error:{e}")     
    except Exception as ex:
        raise Exception(f"error:", str(ex))
