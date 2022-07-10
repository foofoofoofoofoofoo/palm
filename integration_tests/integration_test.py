import requests

print("NOTE: you must restart the server before running these integration tests.")

baseurl = "http://localhost:8080/v1"
emails = [
  "foo1@bar.com",
  "foo2@bar.com",
  "foo3@bar.com",
  "foo4@bar.com",
  "foo5@bar.com",
]

print("Testing user creation...")
user_ids = []
for email in emails:
  res = requests.post(f"{baseurl}/users/", json={"email": email})
  data = res.json()
  assert(data["id"])
  user_ids.append(data["id"])
print("  ...passed")


print("Testing account creation...")
account_ids = []
for user_id in user_ids:
  res = requests.post(f"{baseurl}/accounts/", json={"userId": user_id})
  data = res.json()
  assert(data["id"])
  account_ids.append(data["id"])
print("  ...passed")


print("Testing account deposit...")
for account_id in account_ids:
  res = requests.post(f"{baseurl}/accounts/deposit", json={"accountId": account_id, "amount": 100})
  data = res.json()
  assert(data["id"] == account_id)
  assert(data["balance"] == 100)
print("  ...passed")


print("Testing account transfer...")
def transfer(from_account_id, to_account_id, amount):
  res = requests.post(
    f"{baseurl}/accounts/transfer",
    json={"fromAccountId": from_account_id, "toAccountId": to_account_id, "amount": amount},
  )
  data = res.json()
  return data["success"]

assert(transfer(account_ids[0], account_ids[1], 50))
assert(transfer(account_ids[1], account_ids[2], 50))
assert(transfer(account_ids[2], account_ids[3], 50))
assert(transfer(account_ids[3], account_ids[4], 50))
assert(transfer(account_ids[4], account_ids[0], 50))
print("  ...passed")


print("Testing account query...")
def query(account_id):
  res = requests.get(f"{baseurl}/accounts/{account_id}")
  data = res.json()
  return data
query_result = query(account_ids[0])
assert(query_result["balance"] == 100)
assert(len(query_result["transactions"]) == 3)
assert(query_result["transactions"][0]["ttype"] == "DEPOSIT")
assert(query_result["transactions"][0]["amount"] == 100)
assert(query_result["transactions"][1]["ttype"] == f"TRANSFER TO {account_ids[1]}")
assert(query_result["transactions"][1]["amount"] == -50)
assert(query_result["transactions"][2]["ttype"] == f"TRANSFER FROM {account_ids[4]}")
assert(query_result["transactions"][2]["amount"] == 50)
query_result = query(account_ids[1])
assert(query_result["balance"] == 100)
assert(len(query_result["transactions"]) == 3)
assert(query_result["transactions"][0]["ttype"] == "DEPOSIT")
assert(query_result["transactions"][0]["amount"] == 100)
assert(query_result["transactions"][1]["ttype"] == f"TRANSFER FROM {account_ids[0]}")
assert(query_result["transactions"][1]["amount"] == 50)
assert(query_result["transactions"][2]["ttype"] == f"TRANSFER TO {account_ids[2]}")
assert(query_result["transactions"][2]["amount"] == -50)
query_result = query(account_ids[2])
assert(query_result["balance"] == 100)
assert(len(query_result["transactions"]) == 3)
assert(query_result["transactions"][0]["ttype"] == "DEPOSIT")
assert(query_result["transactions"][0]["amount"] == 100)
assert(query_result["transactions"][1]["ttype"] == f"TRANSFER FROM {account_ids[1]}")
assert(query_result["transactions"][1]["amount"] == 50)
assert(query_result["transactions"][2]["ttype"] == f"TRANSFER TO {account_ids[3]}")
assert(query_result["transactions"][2]["amount"] == -50)
query_result = query(account_ids[3])
assert(query_result["balance"] == 100)
assert(len(query_result["transactions"]) == 3)
assert(query_result["transactions"][0]["ttype"] == "DEPOSIT")
assert(query_result["transactions"][0]["amount"] == 100)
assert(query_result["transactions"][1]["ttype"] == f"TRANSFER FROM {account_ids[2]}")
assert(query_result["transactions"][1]["amount"] == 50)
assert(query_result["transactions"][2]["ttype"] == f"TRANSFER TO {account_ids[4]}")
assert(query_result["transactions"][2]["amount"] == -50)
query_result = query(account_ids[4])
assert(query_result["balance"] == 100)
assert(len(query_result["transactions"]) == 3)
assert(query_result["transactions"][0]["ttype"] == "DEPOSIT")
assert(query_result["transactions"][0]["amount"] == 100)
assert(query_result["transactions"][1]["ttype"] == f"TRANSFER FROM {account_ids[3]}")
assert(query_result["transactions"][1]["amount"] == 50)
assert(query_result["transactions"][2]["ttype"] == f"TRANSFER TO {account_ids[0]}")
assert(query_result["transactions"][2]["amount"] == -50)
print("  ...passed")


print("All tests passed.")
print("NOTE: you must restart the server before running these integration tests again.")
